import axios from 'axios'

// create new axios object
const request = axios.create({
    baseURL: 'http://localhost:9090',   // backend interface address  ip:port
    timeout: 30000  //response time, backend need to respond in 30s
})

// request interceptor
// process the result uniformly
// can add some token
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    // let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    //config.headers['token'] = 'token'  // set headers

    return config
}, error => {
    console.error('request error: ' + error) // for debug
    return Promise.reject(error)
});

// response interceptor
// after response, process the result uniformly
request.interceptors.response.use(
    response => {
        let res = response.data;

        // allow server return string, return string to JSON
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.error('response error: ' + error) // for debug
        return Promise.reject(error)
    }
)

export default request
