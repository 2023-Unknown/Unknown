'use client';

import { Client } from '@stomp/stompjs';

export const WebSocketClient = new Client({
	brokerURL: 'ws://localhost:8080/ws/chat',
	debug: function (str: String) {
		// console.log(str);
	},
	reconnectDelay: 5000,
	heartbeatIncoming: 4000,
	heartbeatOutgoing: 4000,
});
