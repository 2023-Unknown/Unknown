import LoginBox from '../../components/Login';
import Logo from '../../components/Logo';

export default function Main() {
	return (
		<div className='DefaultDiv'>
			<Logo />
			<div className='Main'>
				<div className='MainText'>
					<h1 className='MainTitle'>Meet your anonymous friends </h1>
					<div className='SubText'>
						<p>Make amazing chats with people all over the world in real time.</p>
						<p>
							Starting with English, we are trying to make various language exchanges
							possible.
						</p>
						<p>We are implementing a reporting system for safe chatting. </p>
						<p>
							Users who have been reported multiple times may be permanently ejected.
						</p>
					</div>
				</div>
				<LoginBox />
			</div>
		</div>
	);
}
