### 各微服务端口使用情况

|          Moudule-Name          |       描述       |        Application-Name        | 占用端口  |
| :----------------------------: | :--------------: | :----------------------------: | :-------: |
|     aires-cloud-dependency     | 项目全局依赖管理 |               -                |     -     |
|  aires-cloud-register-eureka   |  Eureka注册中心  |      aires-cloud-register      | 8001-8003 |
|        aires-cloud-auth        |   授权认证中心   |        aires-cloud-auth        |   8101    |
|    aires-cloud-gateway-zuul    |     Zuul网关     |    aires-cloud-gateway-zuul    |   8301    |
|   aires-cloud-server-system    |  微服务提供模块  |   aires-cloud-server-system    |   8201    |
| aires-cloud-server-system-test |  微服务测试模块  | aires-cloud-server-system-test |   8202    |
|   aires-cloud-monitor-admin    |     监控中心     |   aires-cloud-monitor-admin    |   8401    |

### 基础服务设施信息

- **Mysql8数据库**:	`81.68.159.242:3306`,`账户:root`,`密码:5566`
- **Redis数据库**:         `81.68.159.242:6379`