/* 회원 데이터 */

import { atom, selector, useRecoilState, useRecoilValue } from 'recoil';

interface User {
	username: string;
	useremail: string;
	userpassword?: string;
}

export const userState = atom<User>({
	key: 'user',
	default: { username: '', useremail: '', userpassword: '' },
});
