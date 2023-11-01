'use client';

import React, { useState } from 'react';
import Link from 'next/link';
import SignUpModal from './SignUp';

export default function Input() {
	const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 추가

	const modalVisible = () => {
		setIsModalOpen(!isModalOpen);
	};

	return (
		<div>
			<div className='relative flex min-h-screen text-gray-400 antialiased flex-col justify-center overflow-hidden bg-navy-50 py-6 sm:py-12'>
				<div className='relative py-3 sm:w-96 mx-auto text-center'>
					<div className='mt-4 shadow-md rounded-lg text-left'>
						<div className='h-5 bg-navy-100 rounded-t-md'></div>
						<div className='px-8 py-6'>
							<label className='block font-bold text-white'>Username or Email</label>
							<input
								type='text'
								placeholder='Email'
								className='border w-full h-12 px-3 py-4 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
							/>
							<label className='block mt-3 font-bold text-white'>Password</label>
							<input
								type='password'
								placeholder='Password'
								className='border w-full h-12 px-3 py-4 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-600 focus:ring-2 rounded-md'
							/>
							<div className='flex justify-between items-baseline mt-8'>
								<button
									onClick={modalVisible} // 모달 열기 버튼
									type='button'
									className='bg-gray-600 text-white py-2 px-8 rounded-md hover:bg-blue-500'>
									Signup
								</button>
								<Link href='/chatting'>
									<button
										type='submit'
										className='bg-blue-900 text-white py-2 px-8 rounded-md hover:bg-blue-500'>
										Login
									</button>
								</Link>
							</div>
						</div>
					</div>
				</div>
			</div>
			{isModalOpen && <SignUpModal modalVisible={modalVisible} />}
		</div>
	);
}
