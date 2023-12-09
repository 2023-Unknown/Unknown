import axios from 'axios';

/* 채팅방 배정 */
export const getChatroom = async (token: String) => {
	const response = await axios.get('/chat/room/enter', {
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	return response.data;
};

/* 메세지 번역 */
export const translateToKO = async (message: string) => {
	const data = {
		target_lang: 'KO',
		text: [message],
	};
	const response = await axios.post('/translate', data);
	return response.data;
};
