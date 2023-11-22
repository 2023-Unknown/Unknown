'use client';

import React from 'react';
import { useRouter } from 'next/navigation';
import Logo from '../components/Logo';

export default function page() {
	const router = useRouter();

	return (
		<div className='StartPage'>
			<Logo />
			<div className='StartInfo'>
				<p className='StartTitle'>Have a nice time with your new friend</p>
				There may be restrictions on the use of abusive language. If you get a
				warning of more than five days, you will be expelled permanently.
			</div>
			<div className='ButtonBox'>
				<button
					className='StartButton bg-blue-900  hover:bg-blue-500'
					onClick={() => router.push('/login')}>
					Start the chat
				</button>
			</div>
		</div>
	);
}
