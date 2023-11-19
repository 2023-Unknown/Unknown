'use client';
import { useState } from 'react';
import { useRecoilState, useRecoilValue, useSetRecoilState } from 'recoil';
import { userState } from '../states/user';
import { signup } from '../apis/user';

export default function Page() {
	const [formData, setFormData] = useState({
		username: '',
		useremail: '',
		userpassword: '',
	});
	const setUser = useSetRecoilState(userState);

	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setFormData({ ...formData, [name]: value });
	};

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		try {
			const signUpUser = await signup(formData);
			setUser(signUpUser);
		} catch (error) {
			console.error(error); // 이 부분은 에러처리를 원하는 방식으로 변경하실 수 있습니다.
		}
	};

	return (
		<>
			<h1>Hello, SignUp/SignIn Page!</h1>

			<form onSubmit={handleSubmit}>
				<input
					type='text'
					placeholder='userName'
					value={formData.username}
					name='username'
					onChange={handleChange}></input>
				<br />
				<input
					type='email'
					placeholder='userEmail'
					value={formData.useremail}
					name='useremail'
					onChange={handleChange}></input>
				<br />
				<input
					type='password'
					placeholder='userPassword'
					value={formData.userpassword}
					name='userpassword'
					onChange={handleChange}></input>
				<br />
				<button type='submit'>회원가입</button>
			</form>
		</>
	);
}
