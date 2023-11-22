import axios from 'axios';

/* 회원가입 */
export const createUser = async (formData: {
	email: string;
	password: string;
	username: string;
}) => {
	const response = await axios.post('/users/register', formData);
};

/* 이메일 인증 */
export const certifyEmail = async (email: string) => {
	const response = await axios.post('/mail?mail=' + email);
	const certifyNumber = response.data;
	return certifyNumber;
};

/* 로그인 */
export const loginUser = async (formData: {
	loginEmail: string;
	password: string;
}) => {
	const response = await axios.post('/users/login', formData);
	return response.data;
};

/* 로그아웃 */
