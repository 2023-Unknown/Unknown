'use client';

import React, { useState } from 'react';
import Logo from './SignupLogo';
import { userState } from '../states/user';
import { createUser } from '../apis/user';
import { useRecoilState, useRecoilValue, useSetRecoilState } from 'recoil';

export default function SignUp(props: any) {
	const { modalVisible } = props;

	const [formData, setFormData] = useState({
		username: '',
		email: '',
		password: '',
		interest: '',
		introduction: '',
		phoneNumber: '',
		profileImage: '',
		reportCount: 0,
	});
	const setUser = useSetRecoilState(userState);
	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setFormData({ ...formData, [name]: value });
	};

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		// phoneNumber는 서버에서 공백으로 받지 못해서 강제로 email 정보 그대로 입력
		setFormData({ ...formData, phoneNumber: formData.email });
		e.preventDefault();

		try {
			const signUpUser = await createUser(formData);
			modalVisible();
		} catch (error) {
			console.error(error); // 이 부분은 에러처리를 원하는 방식으로 변경하실 수 있습니다.
		}
	};

	return (
		<div>
			<div className='fixed inset-0 flex items-center justify-center z-10 bg-black bg-opacity-50'>
				<div className='bg-white rounded-lg p-8 max-w-md'>
					<div>
						<Logo />
					</div>
					<form onSubmit={handleSubmit}>
						<input
							type='text'
							placeholder='Name'
							value={formData.username}
							name='username'
							onChange={handleChange}
							className='border w-full h-12 px-3 py-4 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
						/>

						<input
							type='email'
							placeholder='Email'
							value={formData.email}
							name='email'
							onChange={handleChange}
							className='border w-full h-12 px-3 py-4 mt-4 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
						/>

						<input
							type='password'
							placeholder='Password'
							value={formData.password}
							name='password'
							onChange={handleChange}
							className='border w-full h-12 px-3 py-4 mt-4 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
						/>
						<button
							type='submit'
							//onClick={modalVisible} // 모달 닫기 버튼
							className='bg-blue-800 font-semibold text-white py-2 px-40 rounded-md hover:bg-blue-600 ml-1 mt-11'>
							Signup
						</button>
					</form>
					<button
						onClick={modalVisible} // 모달 닫기 버튼
						className='bg-blue-800 font-semibold text-white py-2 px-40 rounded-md hover:bg-blue-600 ml-1 mt-6'>
						Cancel
					</button>
				</div>
			</div>
		</div>
	);
}
