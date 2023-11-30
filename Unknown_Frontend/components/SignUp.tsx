'use client';

import React, { useState } from 'react';
import { useRecoilState, useRecoilValue, useSetRecoilState } from 'recoil';
import Logo from './SignupLogo';
import { userState } from '../states/user';
import { createUser, certifyEmail } from '../apis/user';

export default function SignUp(props: any) {
	const { modalVisible } = props;

	const [formData, setFormData] = useState({
		username: '',
		email: '',
		password: '',
	});
	const [code, setCode] = useState('');
	const [returnCode, setReturnCode] = useState('');

	const setUser = useSetRecoilState(userState);

	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setFormData({ ...formData, [name]: value });
	};

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();

		try {
			const authenticateEmail = await certifyEmail(formData.email);
			setReturnCode(authenticateEmail);
		} catch (error) {
			console.error(error); // 이 부분은 에러처리를 원하는 방식으로 변경하실 수 있습니다.
		}
	};

	const handleCodeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setCode(value);
	};

	const checkCodeAndCreateUser = async () => {
		if (returnCode == code) {
			const signUpUser = await createUser(formData);
			modalVisible();
		}
	};

	return (
		<div>
			<div className='fixed inset-0 flex items-center justify-center z-10 bg-black bg-opacity-50'>
				<div className='bg-white rounded-lg p-8 max-w-md'>
					<div>
						<img
							className='LogoSignup'
							src='https://images-eds-ssl.xboxlive.com/image?url=Q_rwcVSTCIytJ0KOzcjWTYl.n38D8jlKWXJx7NRJmQKBAEDCgtTAQ0JS02UoaiwRKJu7IXE5p0ljF.XZq88_C_s3UL1VlzvBplYDodmaXiLfR9P2ktgJS5uJp1QqKV40EEsW4QyOSkEeeG7FBMxo8A--&format=source'></img>
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

					{returnCode !== '' && (
						<>
							<input
								type='text'
								placeholder='Authentication Code'
								value={code}
								name='code'
								onChange={handleCodeChange}
								className='border w-full h-12 px-3 py-4 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
							/>

							<button
								onClick={checkCodeAndCreateUser} // 모달 닫기 버튼
								className='bg-blue-800 font-semibold text-white py-2 px-40 rounded-md hover:bg-blue-600 ml-1 mt-6'>
								Certify Email
							</button>
						</>
					)}
				</div>
			</div>
		</div>
	);
}
