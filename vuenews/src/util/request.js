import Vue from 'vue'
import axios from 'axios'
import qs from 'qs'

axios.defaults.baseURL="http://localhost:8976/gdpuNews/"

export var request =async (type='POST',url='',data={})=> {
    let result
    type = type.toUpperCase()
    if (type === 'GET') {
        await axios.get(url, {params: data}).then(
            res => {
                result = res.data
            })
    } else if (type === 'POST') {
        await axios.post(url, qs.stringify(data)).then(
            res => {
                result = res.data
            })
    }
    return result;    //返回的result是request(Promise)返回的res参数
}