import React, { useState, useRef, useEffect } from 'react';
import { translateToKO } from '../apis/chat';

interface Props {
	index: number;
	css: string;
	message: string;
	tmessage: Promise<string>;
}

const ChatMessage = ({ index, css, message, tmessage }: Props) => {
	console.log(tmessage);
	console.log(typeof tmessage);
	const [isHovering, setIsHovering] = useState(0);

	const translateMessage = async (message: string) => {
		const translateResult = await translateToKO(message)
			.then((res) => {
				console.log(res);
				return res;
			})
			.catch(() => {
				return '';
			});
	};

	return (
		<div
			key={index}
			className={css}
			onMouseEnter={() => setIsHovering(1)}
			onMouseLeave={() => setIsHovering(0)}>
			{isHovering === 0 ? message : tmessage}
		</div>
	);
};

export default ChatMessage;
