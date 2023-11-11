/** @type {import('next').NextConfig} */
// const nextConfig = {};

const nextConfig = {
	reactStrictMode: true,
	swcMinify: true,
	async rewrites() {
		return [
			{
				source: '/:path*',
				destination: 'http://localhost:8080/api/v1/:path*',
			},
		];
	},
};

module.exports = nextConfig;
