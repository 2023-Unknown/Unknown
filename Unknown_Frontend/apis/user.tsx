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
	console.log(formData);
	const response = await axios.post('/users/create', formData);
	console.log(response.data);
	// const { username, useremail, userpassword } = response.data;
	// localStorage.setItem("token", token); // 로컬 스토리지에 토큰 저장
	//return { username, useremail, userpassword };
};

/* 로그아웃 */
//  export const logout
