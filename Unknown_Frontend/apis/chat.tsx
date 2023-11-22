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
