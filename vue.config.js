const ENV = process.env.NODE_ENV;
module.exports={
    devServer:{
        proxy:{
            //通过这种方式的代理，只有在请求的链接添加了前缀名的时候才会触发代理转发
            //格式即“http://localhost:8080/前缀名/请求数据”
            '/server':{
                target:"http://8.130.11.252:8080",
                //但这种情况会导致转发给目标服务器时仍然带着这个前缀名，而目标服务器上没有这个前缀名路径
                //所以会导致请求出错，因此这里通过pathRewrite来对路径进行一个正则化处理，去掉前缀名
                pathRewrite:{'^/server':''} //匹配所有带前缀名的字符串，将前缀名替换成空字符串
                //ws:true        //用于支持websocket
                //若为true则是如实提供请求地址（即本机），若为false则向目标服务器提供一个假的请求地址
                //例如此处，如果为真，则为localhost:8080，若为假，则为"目标服务器“
                //changeOrigin:false     
            }
        },
    },
    publicPath:ENV === '/deviceManageSystem',
}