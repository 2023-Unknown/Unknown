'use client';

import React, { useEffect, useState } from 'react';
import { getCookie } from '@/config/cookie';
import Logo from '../../components/Logo';
import ChatPrompt from '@/components/ChatPrompt';
import { Spinner } from '../../components/Spinner';
import { getChatroom } from '@/apis/chat';
import { Client } from '@stomp/stompjs';
import { WebSocket } from 'ws';

export default function Main() {
	const [roomId, setRoomId] = useState(0);

	const [ws, setWs] = useState<Client | null>(null);
	const tokenState = useRecoilValue(userToken);
	let promiseRoomId = "";

	const enterChat = async () => {
		try {
			const enterChat = await getChatroom(getCookie('userToken')).then((res) => {
        promiseRoomId = res;
				setRoomId(res);

				const WebSocketClient = new Client({
					brokerURL: 'ws://localhost:8080/ws/chat',
					debug: function (str) {
						console.log(str);
					},
					reconnectDelay: 5000,
					heartbeatIncoming: 4000,
					heartbeatOutgoing: 4000,

					onConnect: () => {
						console.log('onConnect');
						// 123부분에 roomId를 넣으면 됩니다.
						WebSocketClient.subscribe('/topic/chat/room/123', (message) => {
							console.log(`Received Message : ${message.body}`);
						});
					}

				});
				WebSocketClient.activate();
				setWs(WebSocketClient);
			});
		} catch (error) {
			console.error(error); // 이 부분은 에러처리를 원하는 방식으로 변경하실 수 있습니다.
		}
	};

	// 첫 렌더링에서 랜덤 채팅방 호출
	// -> 랜덤 채팅방 매칭 전 또는 api가 이상할 때, 로딩 컴포넌트
	// -> 랜덤 채팅방 매칭 후, 채팅방 컴포넌트 뜰 것
	useEffect(() => {
		enterChat();
		console.log("roomId: " + promiseRoomId);
	}, [userToken]);

	return (
		<div>
			<Logo />
			{roomId === 0 ? <Spinner /> : <ChatPrompt ws={ws} />}
		</div>
	);
}
