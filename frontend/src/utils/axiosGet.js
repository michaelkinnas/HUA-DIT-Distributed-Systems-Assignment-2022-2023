import axios from 'axios';


export const axiosGet = async (url, payload) => {
    const data = await axios.get(url, payload);
    return data;
};
