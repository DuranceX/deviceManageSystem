import axios from "axios"

export const checkLogin = async function(username,password){
    // let data={
    //     username:username,
    //     password:password,
    // }
    // console.log(qs.stringify(data))
    // axios.post("/server/login",{
    //     data:qs.stringify(data)
    // },{
    //     headers:{'Content-Type':'application/x-www-form-urlencoded'}
    // }).then(
    //     response => {
    //         console.log(response);
    //     }
    // ).catch(
    //     (error)=>{
    //         console.log(error);
    //     }
    // )

    return new Promise((resolve,reject)=>{
        axios.post("/server/login","username=" + username + "&password=" + password)
        .then(res => {
            resolve(res.data);
        }).catch(err =>{
            reject(err.data);
        });
    });
}