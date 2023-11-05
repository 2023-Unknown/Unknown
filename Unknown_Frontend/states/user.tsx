/* 회원 데이터 */

import { atom, selector, useRecoilState, useRecoilValue } from 'recoil';

interface CreateUser {
	email: string;
	interest: string;
	introduction: string;
	password?: string;
	phoneNumber: string;
	profileImage: string;
	reportCount: number;
	username: string;
}

export const userState = atom<CreateUser>({
	key: 'user',
	default: {
		username: '',
		email: '',
		password: '',
		interest: '',
		introduction: '',
		phoneNumber: '',
		profileImage: '',
		reportCount: 0,
	},
});
