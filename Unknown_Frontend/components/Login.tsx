'use client';

import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { setCookie } from '@/config/cookie';
import { useSetRecoilState, useRecoilValue, useRecoilState } from 'recoil';
import SignUpModal from './SignUp';
import { userToken, userEmail } from '../states/user';
import { loginUser } from '../apis/user';

export default function Input() {
	const router = useRouter();

	const token = useRecoilValue(userToken);

	const setToken = useSetRecoilState(userToken);
	const setEmail = useSetRecoilState(userEmail);

	const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 추가
	const [loginData, setLoginData] = useState({
		loginEmail: '',
		password: '',
	});


	const modalVisible = () => {
		setIsModalOpen(!isModalOpen);
	};

	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setLoginData({ ...loginData, [name]: value });
	};

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		try {
			const getToken = await loginUser(loginData).then((res) => {
				setToken(res);
				setCookie('userToken', res, []);
				setEmail(loginData.loginEmail);
				setCookie('userEmail', loginData.loginEmail, []);
				router.push('/chatting');
			});
		} catch (error) {
			console.error(error); // 이 부분은 에러처리를 원하는 방식으로 변경하실 수 있습니다.
		}
	};

	return (
		<div className='LoginInput'>
			<form onSubmit={handleSubmit}>
				<div className='px-8 py-6'>
					<label className='block font-bold text-white'>Email</label>
					<input
						type='text'
						placeholder='Email'
						name='loginEmail'
						onChange={handleChange}
						className='border w-full h-12 px-3 py-4 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
					/>
					<label className='block mt-3 font-bold text-white'>Password</label>
					<input
						type='password'
						placeholder='Password'
						name='password'
						onChange={handleChange}
						className='border w-full h-12 px-3 py-4 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
					/>
					<div className='flex justify-between items-baseline mt-8'>
						<button
							onClick={modalVisible} // 모달 열기 버튼
							type='button'
							className='bg-gray-600 text-white py-2 px-8 rounded-md hover:bg-blue-500'>
							Signup
						</button>

						<button
							type='submit'
							className='bg-blue-900 text-white py-2 px-8 rounded-md hover:bg-blue-500'>
							Login
						</button>
					</div>
				</div>
			</form>

			{isModalOpen && <SignUpModal modalVisible={modalVisible} />}
		</div>
	);
}
