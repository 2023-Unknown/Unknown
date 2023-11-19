'use client';

import React from 'react';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import Logo from '../../components/Logo';
import { userToken } from '../../states/user';

export default function Main() {
	const token = useRecoilValue(userToken);

	return (
		<div>
			<Logo />
		</div>
	);
}
