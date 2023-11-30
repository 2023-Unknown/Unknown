'use client';

import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { getCookie, removeCookie } from '@/config/cookie';
import Logo from '../components/Logo';
import Image from 'next/image';

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
		<div className='DefaultDiv'>
			<Logo />
			<div className='CenterDiv'>
				<p className='StartTitle'>Have a nice time with your new friend</p>
				<p className='Warning'>
					There may be restrictions on the use of abusive language.
				</p>
				<p className='Warning'>
					If you get a warning of more than five days, you will be expelled
					permanently.
				</p>
				<div className='ButtonBox'>
					<button
						className='StartButton bg-blue-900  hover:bg-blue-500'
						onClick={() => router.push(path)}>
						Start the chat
					</button>
				</div>
			</div>
			<div className='BottomDiv'>
				{isLogined && (
					<button className='LogoutButton' onClick={logout}>
						<Image src='/logout.png' alt='logout' width='60' height='60' />
						See u next time!
					</button>
				)}
			</div>
		</div>
	);
}
