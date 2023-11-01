'use client'

import React, { useState, useRef, useEffect } from 'react';

export default function ChatPrompt() {
  const [message, setMessage] = useState<string>('');
  const [messages, setMessages] = useState<string[]>([]);
  const chatMessagesRef = useRef<HTMLDivElement>(null);

  const chatInputRef = useRef<HTMLInputElement>(null);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMessage(e.target.value);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (message.trim() !== '') {
      setMessages([...messages, message]);
      setMessage('');
    }
  };

  // 스크롤을 항상 가장 밑으로 이동
  const scrollToBottom = () => {
    if (chatMessagesRef.current) {
      chatMessagesRef.current.scrollTop = chatMessagesRef.current.scrollHeight;
    }
  };

  useEffect(() => {
    // 메시지가 추가될 때 스크롤을 맨 아래로 이동
    scrollToBottom();
  }, [messages]);

  return (
    <div>
      <div className='ChatBox'>
        <div ref={chatMessagesRef} className='ChatMessages'>
          {messages.map((msg, index) => (
            <div key={index} className='ChatMessage'>
              {msg}
            </div>
          ))}
        </div>
        <div className='ChatInputBox'>
          <form onSubmit={handleSubmit}>
            <input
              ref={chatInputRef}
              className='ChatInput'
              type="text"
              value={message}
              onChange={handleInputChange}
              placeholder="Type your message..."
            />
            <button className='ChatButton' type="submit">
              Send
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

