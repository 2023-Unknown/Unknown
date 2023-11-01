/* 회원 데이터 */

import { atom, selector, useRecoilState, useRecoilValue } from 'recoil';

interface CreateUser {
	username: string;
	useremail: string;
	userpassword?: string;
}

export const userState = atom<CreateUser>({
	key: 'user',
	default: { username: '', useremail: '', userpassword: '' },
});
