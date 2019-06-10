step_1:
    clone.sh 拉取代码


step_2:
    compile.sh 编译代码生成镜像
    
step_3;
    buildImage.sh 生成本地镜像
    
step_4:
    uploadLocalDockerImageToAliYun.sh 上传本地镜像到阿里云镜像库


step_5:
    deployAppByAliYunImage.sh 部署镜像从阿里云镜像库拉取·1