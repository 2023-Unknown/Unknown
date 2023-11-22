'use client';

import React, { useState, useRef, useEffect } from 'react';

export default function ChatPrompt() {
	const [message, setMessage] = useState<string>('');
	const [messages, setMessages] = useState<string[]>([]);
	const chatMessagesRef = useRef<HTMLDivElement | null>(null);
	const chatInputRef = useRef<HTMLInputElement>(null);

	const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setMessage(e.target.value);
	};

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault();
		if (message.trim() !== '') {
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
		<div>
			<div className='ChatBox'>
				<div className='ChatMessages' ref={chatMessagesRef}>
					{' '}
					{/* 수정된 부분 */}
					{messages.map((msg, index) => (
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
		</div>
	);
}
