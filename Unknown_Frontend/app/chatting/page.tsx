'use client';

import React, { useEffect, useState } from 'react';
import { getCookie } from '@/config/cookie';
import Logo from '../../components/Logo';
import ChatPrompt from '@/components/ChatPrompt';
import { Spinner } from '../../components/Spinner';
import { getChatroom } from '@/apis/chat';

export default function Main() {
	const [roomId, setRoomId] = useState('');
	useEffect(() => {
		enterChat();
	}, []);
	const enterChat = async () => {
		const enterChat = await getChatroom(getCookie('userToken')).then((res) => {
			setRoomId(res.roomId);
		});
	};
	return (
		<div>
			<Logo />
			{roomId === '' ? <Spinner /> : <ChatPrompt room={roomId} />}
		</div>
	);
}
