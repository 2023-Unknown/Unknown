import React, { useEffect, useState } from 'react';
import { translateToKO } from '../apis/chat';

interface Props {
	index: number;
	css: string;
	message: string;
	tmessage: string;
}

export default function ChatMessage(props: Props) {
	const [isHovering, setIsHovering] = useState(0);
	const [tm, setTm] = useState<string | undefined>(undefined);

	useEffect(() => {
		const translateMessage = async (message: string) => {
			const res = await translateToKO(message);
			console.log(res);
			setTm(res);
		};

		translateMessage(props.message);
	}, [props.message]);

	return (
		<div
			key={props.index}
			className={props.css}
			onMouseEnter={() => setIsHovering(1)}
			onMouseLeave={() => setIsHovering(0)}>
			{isHovering === 0 ? props.message : tm}
		</div>
	);
}
