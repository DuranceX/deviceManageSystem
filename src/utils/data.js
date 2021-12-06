import axios from "axios"

export const checkLogin = (username,password) => {
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

    console.log("@@@",'username:',username,'password:',password);
    axios.post("/server/login","username='" + username + "'&password='" + password + "'")
    .then((respose)=>{
        console.log(respose.data);
    });
}