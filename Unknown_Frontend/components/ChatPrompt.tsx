'use client';

import React, { useState, useRef, useEffect } from 'react';
import { WebSocketClient } from '../config/websocket';
import { useRouter } from 'next/navigation';
import Image from 'next/image';
import { translateToKO } from '../apis/chat';
import ChatMessage from '../components/ChatMessage';
import { IMessage } from '../states/interface';
import { useRecoilValue } from 'recoil';
import { userEmail } from '../states/user';
import { getCookie } from '@/config/cookie';
export default function ChatPrompt(room: string) {
	const router = useRouter();
	const [isHovering, setIsHovering] = useState(0);
	const [message, setMessage] = useState<string>(''); // 보낼 메세지 (1개)
	const [chat, setChat] = useState<IMessage[]>([]); // 전체 메세지들
	const chatMessagesRef = useRef<HTMLDivElement | null>(null);
	const chatInputRef = useRef<HTMLInputElement>(null);

	const myEmail = getCookie('userEmail');

	useEffect(() => {
		scrollToBottom();
	}, [chat]);

	// 스크롤을 항상 가장 아래로 이동
	const scrollToBottom = () => {
		if (chatMessagesRef.current) {
			chatMessagesRef.current.scrollIntoView({ behavior: 'smooth', block: 'end' });
		}
	};

	// WebSocket 연결
	WebSocketClient.onConnect = () => {
		console.log(room.room);
		WebSocketClient.subscribe(
			'/topic/chat/room/' + room.room,
			(message: string) => {
				// Received Message : {"type":"TALK","roomId":"123","sender":"heesane","message":"hi"}
				handleReceivedMessage(message);
			},
		);
	};
	WebSocketClient.activate();

	const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setMessage(e.target.value);
	};

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault();
		if (message.trim() === '') return;
		console.log(myEmail);
		WebSocketClient?.publish({
			destination: '/app/chat/message',
			body: JSON.stringify({
				type: 'TALK',
				sender: myEmail,
				message: message,
				roomId: room.room,
			}),
		});
		setMessage('');
	};

	const handleReceivedMessage = (message: string) => {
		let data = JSON.parse(message.body); // String -> Json으로 변환
		console.log(data);
		let msg = {
			user: data.sender,
			message: data.message,
		};
		chat.push(msg);
		setChat([...chat]);
	};

	const translateMessage = async (message: string) => {
		await translateToKO(message)
			.then((res) => {
				return res;
			})
			.catch(() => {
				return '';
			});
	};

	const reportUser = () => {
		/*신고버튼*/
	};

	const exitRoom = () => {
		router.push('/');
	};

	return (
		<>
			<div className='ToolBox'>
				{/* <button onClick={reportUser} className='ReportButton'>
					<Image src='/report.png' alt='report' width='35' height='30' />
				</button> */}
				<button onClick={exitRoom} className='ExitButton'>
					<Image src='/exit.png' alt='exit' width='60' height='60' />
				</button>
			</div>
			<div className='ChatBox'>
				<div className='ChatMessages' ref={chatMessagesRef}>
					{' '}
					{chat.map((chat, index) => {
						console.log(chat.user)
						console.log(myEmail)
						let css = chat.user === myEmail ? 'MyMessage' : 'OtherMessage';
						// let msg = chat.message;
						return (
							<div>
								<ChatMessage
									index={index}
									css={css}
									message={chat.message}
									tmessage={chat.tmessage}
								/>
							</div>
						);
					})}{' '}
				</div>
			</div>
			<form onSubmit={handleSubmit}>
				<div className='ChatInputBox'>
					<input
						ref={chatInputRef}
						className='ChatInput'
						type='text'
						value={message}
						onChange={handleInputChange}
						placeholder='   Type your message'
					/>
				</div>
				<button className='ChatButton' type='submit'>
					<Image src='/sendMessage.png' alt='send' width='40' height='40' />
				</button>
			</form>
		</>
	);
}
