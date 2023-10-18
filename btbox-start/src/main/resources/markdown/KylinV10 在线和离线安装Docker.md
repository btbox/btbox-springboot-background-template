# KylinV10 在线和离线安装Docker



# 1.在线安装

```shell
# 配置docker仓库
yum install -y yum-utils
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# 正式安装
yum install docker-ce docker-ce-cli containerd.io
# 启动
systemctl start docker
# 验证
docker -v
Docker version 20.10.14, build a224086
```


# 2.离线安装配置

## rpm版本安装
```shell
# 系统版本
rpm -qi centos-release
Name        : centos-release
Version     : 8.1
Release     : 1.1911.0.8.el8
Architecture: x86_64
```

- Install Docker Engine On 不同版本 下载地址，当前使用的是 对应版本目录下的 docker-ce-20.10.13-3.el8.x86_64.rpm

```shell
# 安装 yum 会自动安装依赖
yum install /path/to/package.rpm
# 启动
systemctl start docker
# 验证
docker run hello-world
```

- 如果 yum源 没有问题，也可直接使用yum install docker-ce命令进行安装。

## tgz版安装

解压版[下载地址](https://download.docker.com/linux/static/stable/x86_64/)，当前使用的是docker-20.10.13.tgz。

```shell
# 1.解压
tar -zxvf docker-20.10.13.tgz
# 2.移动到 /usr/bin/ 目录下【不要放到其他文件夹下】
mv docker/* /usr/bin/
# 3.添加 service 服务
vim /etc/systemd/system/docker.service
```

```
[Unit]
Description=Docker Application Container Engine
Documentation=https://docs.docker.com
After=network-online.target firewalld.service
Wants=network-online.target

[Service]
Type=notify
ExecStart=/usr/bin/dockerd
ExecReload=/bin/kill -s HUP $MAINPID
TimeoutSec=0
RestartSec=2
Restart=always

StartLimitBurst=3
StartLimitInterval=60s

LimitNOFILE=infinity
LimitNPROC=infinity
LimitCORE=infinity

TasksMax=infinity
Delegate=yes
KillMode=process

[Install]
WantedBy=multi-user.target
```


```shell
# 4.重载 unit 配置文件
systemctl daemon-reload
# 5.启动服务并查看状态
systemctl start docker
systemctl status docker
# 版本验证
[root@aliyun /]# docker version
Client:
 Version:           20.10.13
 API version:       1.40
 Go version:        go1.16.15
 Git commit:        a224086
 Built:             Thu Mar 10 14:01:44 2022
 OS/Arch:           linux/amd64
 Context:           default
 Experimental:      true
# 设置开机自启
systemctl enable docker.service                       
```