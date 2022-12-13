import axios from 'axios';


export const axiosPost = async (url, payload) => {
	const data = await axios.post(url, payload);
	return data;
};
