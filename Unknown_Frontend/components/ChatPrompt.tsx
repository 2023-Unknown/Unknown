'use client';

import React, { useState, useRef, useEffect } from 'react';
import { WebSocketClient } from '../config/websocket';

export default function ChatPrompt(room: string) {
	const [message, setMessage] = useState<string>('');
	const [messages, setMessages] = useState<string[]>([]);
	const [omessages, setOMessages] = useState<string[]>([]);
	const chatMessagesRef = useRef<HTMLDivElement | null>(null);
	const chatInputRef = useRef<HTMLInputElement>(null);

	WebSocketClient.onConnect = () => {
		WebSocketClient.subscribe('/topic/chat/room/123', (message) => {
			// Received Message : {"type":"TALK","roomId":"123","sender":"heesane","message":"hi"}
			let msg = JSON.parse(message.body); // String -> Json으로 변환
			setOMessages([...omessages, msg.message]);
			console.log(`${msg.sender} : ${msg.message}`);
		});
	};
	WebSocketClient.activate();

	const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setMessage(e.target.value);
	};

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault();
		if (message.trim() !== '') {
			// 추가된 부분 11.29
			WebSocketClient?.publish({
				destination: '/app/chat/message',
				body: JSON.stringify({
					type: 'TALK',
					sender: 'heesane',
					message: message,
					roomId: '123',
				}),
			});

			setMessages([...messages, message]);
			setMessage('');
			// 스크롤을 항상 가장 아래로 이동
			scrollToBottom();
		}
	};

	// 스크롤을 항상 가장 아래로 이동
	const scrollToBottom = () => {
		if (chatMessagesRef.current) {
			chatMessagesRef.current.scrollIntoView({ behavior: 'smooth', block: 'end' });
		}
	};

	useEffect(() => {
		// 메시지가 추가될 때 스크롤을 맨 아래로 이동
		scrollToBottom();
	}, [messages]);

	return (
		<div className='ChatBox'>
			<div className='ChatMessages' ref={chatMessagesRef}>
				{' '}
				{/* 수정된 부분 */}
				{messages.map((msg, index) => (
					<div key={index} className='Message'>
						{msg}
					</div>
				))}{' '}
				{omessages.map((msg, index) => (
					<div key={index} className='Message'>
						{msg}
					</div>
				))}{' '}
				{/* 수정된 부분 */}
			</div>
			<div className='ChatInputBox'>
				<form onSubmit={handleSubmit}>
					<input
						ref={chatInputRef}
						className='ChatInput'
						type='text'
						value={message}
						onChange={handleInputChange}
						placeholder='Type your message'
					/>
					<button className='ChatButton' type='submit'>
						Send
					</button>
				</form>
			</div>
		</div>
	);
}
