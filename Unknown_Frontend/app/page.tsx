'use client';

import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { getCookie, removeCookie } from '@/config/cookie';
import Logo from '../components/Logo';

export default function page() {
	const router = useRouter();
	const isLogined = getCookie('userToken') === undefined ? false : true; // 로그인 유무
	// 로그인 했을 경우, 다음 페이지 채팅페이지
	// 로그인 안했을 경우, 다음 페이지 로그인 페이지
	const [path, setPath] = useState(isLogined ? '/chatting' : '/login');
	// 로그아웃 이벤트
	const logout = () => {
		removeCookie('userToken');
		setPath('/login');
	};

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
					onClick={() => router.push(path)}>
					Start the chat
				</button>
				{isLogined && (
					<button
						className='StartButton bg-blue-900  hover:bg-blue-500'
						onClick={logout}>
						Logout
					</button>
				)}
			</div>
		</div>
	);
}
