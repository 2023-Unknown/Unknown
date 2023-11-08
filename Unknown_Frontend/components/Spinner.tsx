import Image from 'next/image';

export const Spinner = () => {
	return (
		<div>
			<Image src='/loadingEarth.gif' alt='spinner' width='120' height='120' />
		</div>
	);
};
