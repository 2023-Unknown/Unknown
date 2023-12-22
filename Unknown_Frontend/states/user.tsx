/* 회원 데이터 */

import { atom, selector, useRecoilState, useRecoilValue } from 'recoil';

interface CreateUser {
	email: string;
	password?: string;
	username: string;
}

interface LoginUser {
	loginEmail: string;
	password?: string;
}

export const userState = atom<CreateUser>({
	key: 'user',
	default: {
		username: '',
		email: '',
		password: '',
	},
});

export const loginUserState = atom<LoginUser>({
	key: 'Loginuser',
	default: {
		loginEmail: '',
		password: '',
	},
});

export const userToken = atom({
	key: 'token',
	default: {
		token: 'Bearer',
	},
});

export const userEmail = atom({
	key: 'email',
	default: '',
});
