import axios from 'axios';

/* 회원가입 */
export const createUser = async (formData: {
	email: string;
	interest: string;
	introduction: string;
	password: string;
	phoneNumber: string;
	profileImage: string;
	reportCount: number;
	username: string;
}) => {
	const response = await axios.post('/api/v1/users/create', formData);
	console.log(response.data);
};

/* 이메일 인증 */
export const certifyEmail = async (email: string) => {
	const response = await axios.post('/mail?mail=' + email);
	const certifyNumber = response.data;
	console.log(certifyNumber);
	return certifyNumber;
};

/* 로그인 */

/* 로그아웃 */
//  export const logout
