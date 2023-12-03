'use client';

import React, { useState, useRef, useEffect } from 'react';
import { WebSocketClient } from '../config/websocket';
import { useRouter } from 'next/navigation';
import Image from 'next/image';

interface IMessage {
	user: string;
	message: string;
}

export default function ChatPrompt(room: string) {
	const router = useRouter();

	const [message, setMessage] = useState<string>(''); // 보낼 메세지 (1개)
	const [chat, setChat] = useState<IMessage[]>([]); // 전체 메세지들
	const chatMessagesRef = useRef<HTMLDivElement | null>(null);
	const chatInputRef = useRef<HTMLInputElement>(null);

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
		WebSocketClient.subscribe('/topic/chat/room/123', (message: string) => {
			// Received Message : {"type":"TALK","roomId":"123","sender":"heesane","message":"hi"}
			handleReceivedMessage(message);
		});
	};
	WebSocketClient.activate();

	const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setMessage(e.target.value);
	};

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault();
		if (message.trim() === '') return;

		WebSocketClient?.publish({
			destination: '/app/chat/message',
			body: JSON.stringify({
				type: 'TALK',
				sender: 'DefaultUser',
				message: message,
				roomId: '123',
			}),
		});
		setMessage('');
	};

	const handleReceivedMessage = (message: string) => {
		let data = JSON.parse(message.body); // String -> Json으로 변환
		let msg = {
			user: data.sender,
			message: data.message,
		};
		chat.push(msg);
		setChat([...chat]);
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
				<button onClick={reportUser} className='ReportButton'>
					<Image src='/report.png' alt='report' width='35' height='30' />
				</button>
				<button onClick={exitRoom} className='ExitButton'>
					<Image src='/exit.png' alt='exit' width='50' height='80' />
				</button>
			</div>
			<div className='ChatBox'>
				<div className='ChatMessages' ref={chatMessagesRef}>
					{' '}
					{chat.map((chat, index) => {
						let css = chat.user === 'DefaultUser' ? 'MyMessage' : 'OtherMessage';
						return (
							<div key={index} className={css}>
								{chat.message}
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
