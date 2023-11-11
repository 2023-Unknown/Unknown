/* 회원 데이터 */

import { atom, selector, useRecoilState, useRecoilValue } from 'recoil';

interface CreateUser {
	email: string;
	password?: string;
	username: string;
}

export const userState = atom<CreateUser>({
	key: 'user',
	default: {
		username: '',
		email: '',
		password: '',
	},
});
