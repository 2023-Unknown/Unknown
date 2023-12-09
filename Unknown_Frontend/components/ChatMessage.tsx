import React, { useState } from 'react';
import { translateToKO } from '../apis/chat';

interface Props {
	index: number;
	css: string;
	message: string;
	tmessage: string;
}

export default function ChatMessage(props: Props) {
	const [isHovering, setIsHovering] = useState(0);
	console.log(props.message);
	console.log(props.tmessage);

	const translateMessage = async (message: string) => {
		await translateToKO(message)
			.then((res) => {
				console.log(res);
				return res;
			})
			.catch(() => {
				return '';
			});
	};

	const hw = translateMessage(props.message).then((res) => console.log(res));
	console.log(hw);

	return (
		<div
			key={props.index}
			className={props.css}
			onMouseEnter={() => setIsHovering(1)}
			onMouseLeave={() => setIsHovering(0)}>
			{isHovering === 0 ? props.message : props.tmessage}
		</div>
	);
}
