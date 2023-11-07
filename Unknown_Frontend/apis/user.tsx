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
	const response = await axios.post('/users/create', formData);
	console.log(response.data);
};

/* 로그아웃 */
//  export const logout
