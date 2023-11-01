import LoginBox from '../../components/Login';

export default function Main() {
	return (
		<div className='Main'>
			<div className='MainTitle'>
				<img
					className='Dice'
					src='https://images-eds-ssl.xboxlive.com/image?url=Q_rwcVSTCIytJ0KOzcjWTYl.n38D8jlKWXJx7NRJmQKBAEDCgtTAQ0JS02UoaiwRKJu7IXE5p0ljF.XZq88_C_s3UL1VlzvBplYDodmaXiLfR9P2ktgJS5uJp1QqKV40EEsW4QyOSkEeeG7FBMxo8A--&format=source'></img>
				Unknown
			</div>{' '}
			<h1 className='MainInfo'>Meet your anonymous friends </h1>
			<div className='MainBox'>
				<p className='SubInfo'>
					{' '}
					Make amazing chats with people all over the world in real time. Starting
					with English, we are trying to make various language exchanges possible. We
					are implementing a reporting system for safe chatting. Users who have been
					reported multiple times may be permanently ejected.
				</p>
			</div>
			<div className='InputBox'>
				<LoginBox />
			</div>
		</div>
	);
}
