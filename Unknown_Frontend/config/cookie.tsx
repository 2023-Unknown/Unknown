import { Cookies } from 'react-cookie';

const cookies = new Cookies();

export const setCookie = (name: String, value: String, options: []) => {
	return cookies.set(name, value, { ...options });
};

export const getCookie = (name: String) => {
	return cookies.get(name);
};

export const removeCookie = (name: String) => {
	return cookies.remove(name);
};
