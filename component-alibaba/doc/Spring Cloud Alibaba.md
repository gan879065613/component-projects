# Spring Cloud Alibaba是什么

Spring Cloud 本身并不是一个拿来即可用的框架，它是一套微服务规范，这套规范共有两代实现。

- 第一代实现： Spring Cloud Netflix，
- 第二代实现： Spring Cloud Alibaba。


2018 年 12 月12 日，Netflix 公司宣布 Spring Cloud Netflix 系列大部分组件都进入维护模式，不再添加新特性。这严重地限制了 Spring Cloud 的高速发展，于是各大互联网公司和组织开始把目光转向 Spring Cloud 的第二代实现：Spring Cloud Alibaba。

## Spring Cloud Alibaba

Spring Cloud Alibaba 是阿里巴巴结合自身丰富的微服务实践而推出的微服务开发的一站式解决方案，是 Spring Cloud 第二代实现的主要组成部分。

Spring Cloud Alibaba 吸收了 Spring Cloud Netflix 的核心架构思想，并进行了高性能改进。自 Spring Cloud Netflix 进入停更维护后，Spring Cloud Alibaba 逐渐代替它成为主流的微服务框架。

Spring Cloud Alibaba 是国内首个进入 Spring 社区的开源项目。2018 年 7 月，Spring Cloud Alibaba 正式开源，并进入 Spring Cloud 孵化器中孵化；2019 年 7 月，Spring Cloud 官方宣布 Spring Cloud Alibaba 毕业，并将仓库迁移到 Alibaba Github OSS 下。

虽然 Spring Cloud Alibaba 诞生时间不久，但俗话说的好“大树底下好乘凉”，依赖于阿里巴巴强大的技术影响力，Spring Cloud Alibaba 在业界得到了广泛的使用，成功案例也越来越多。

## Spring Cloud Alibaba 组件

Spring Cloud Alibaba 包含了多种开发分布式微服务系统的必需组件

- Nacos：阿里巴巴开源产品，一个更易于构建云原生应用的动态服务发现,配置管理和服务管理平台。
- Sentinel：阿里巴巴开源产品，把流量作为切入点,从流量控制,熔断降级,系统负载保护等多个维度保护服务的稳定性。
- RocketMQ：Apache RocketMQ 是一款基于Java 的高性能、高吞吐量的分布式消息和流计算平台。
- Dubbo：Apache Dubbo 是一款高性能的 Java RPC 框架。
- Seata：阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。
- Alibaba Cloud OSS：阿里云对象存储服务器（Object Storage Service，简称OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。
- Alibaba Cloud Schedulerx：阿里中间件团队开发的一款分布式调度产品,支持周期性的任务与固定时间点触发任务。


通过 Spring Cloud Alibaba 的这些组件，我们只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里微服务解决方案，通过阿里中间件来迅速搭建分布式应用系统。

## Spring Cloud Alibaba 的应用场景

Spring Cloud Alibaba 的应用场景如下：

- 大型复杂的系统，例如大型电商系统。
- 高并发系统，例如大型门户网站、商品秒杀系统。
- 需求不明确，且变更很快的系统，例如创业公司业务系统。

## Spring Cloud 两代实现组件对比

下表展示了 Spring Cloud 两代实现的组件对比情况。



| Spring Cloud 第一代实现（Netflix） | 状态                                             | Spring Cloud 第二代实现（Alibaba） | 状态                                                 |
| ---------------------------------- | ------------------------------------------------ | ---------------------------------- | ---------------------------------------------------- |
| Ereka                              | 2.0 孵化失败                                     | Nacos Discovery                    | 性能更好，感知力更强                                 |
| Ribbon                             | 停更进维                                         | Spring Cloud Loadbalancer          | Spring Cloud 原生组件，用于代替 Ribbon               |
| Hystrix                            | 停更进维                                         | Sentinel                           | 可视化配置，上手简单                                 |
| Zuul                               | 停更进维                                         | Spring Cloud Gateway               | 性能为 Zuul 的 1.6 倍                                |
| Spring Cloud Config                | 搭建过程复杂，约定过多，无可视化界面，上手难点大 | Nacos Config                       | 搭建过程简单，有可视化界面，配置管理更简单，容易上手 |

## Spring Cloud Alibaba 版本依赖

Spring Cloud、Spring Cloud Alibaba 以及 Spring Boot 之间版本依赖关系如下。



| Spring Cloud 版本           | Spring Cloud Alibaba 版本           | Spring Boot 版本 |
| --------------------------- | ----------------------------------- | ---------------- |
| Spring Cloud 2020.0.1       | 2021.1                              | 2.4.2            |
| Spring Cloud Hoxton.SR12    | 2.2.7.RELEASE                       | 2.3.12.RELEASE   |
| Spring Cloud Hoxton.SR9     | 2.2.6.RELEASE                       | 2.3.2.RELEASE    |
| Spring Cloud Greenwich.SR6  | 2.1.4.RELEASE                       | 2.1.13.RELEASE   |
| Spring Cloud Hoxton.SR3     | 2.2.1.RELEASE                       | 2.2.5.RELEASE    |
| Spring Cloud Hoxton.RELEASE | 2.2.0.RELEASE                       | 2.2.X.RELEASE    |
| Spring Cloud Greenwich      | 2.1.2.RELEASE                       | 2.1.X.RELEASE    |
| Spring Cloud Finchley       | 2.0.4.RELEASE（停止维护，建议升级） | 2.0.X.RELEASE    |
| Spring Cloud Edgware        | 1.5.1.RELEASE（停止维护，建议升级） | 1.5.X.RELEASE    |

## Spring Cloud Alibaba 组件版本关系

Spring Cloud Alibaba 下各组件版本关系如下表。



| Spring Cloud Alibaba 版本                                 | Sentinel 版本 | Nacos 版本 | RocketMQ 版本 | Dubbo 版本 | Seata 版本 |
| --------------------------------------------------------- | ------------- | ---------- | ------------- | ---------- | ---------- |
| 2.2.7.RELEASE                                             | 1.8.1         | 2.0.3      | 4.6.1         | 2.7.13     | 1.3.0      |
| 2.2.6.RELEASE                                             | 1.8.1         | 1.4.2      | 4.4.0         | 2.7.8      | 1.3.0      |
| 2021.1 or 2.2.5.RELEASE or 2.1.4.RELEASE or 2.0.4.RELEASE | 1.8.0         | 1.4.1      | 4.4.0         | 2.7.8      | 1.3.0      |
| 2.2.3.RELEASE or 2.1.3.RELEASE or 2.0.3.RELEASE           | 1.8.0         | 1.3.3      | 4.4.0         | 2.7.8      | 1.3.0      |
| 2.2.1.RELEASE or 2.1.2.RELEASE or 2.0.2.RELEASE           | 1.7.1         | 1.2.1      | 4.4.0         | 2.7.6      | 1.2.0      |
| 2.2.0.RELEASE                                             | 1.7.1         | 1.1.4      | 4.4.0         | 2.7.4.1    | 1.0.0      |
| 2.1.1.RELEASE or 2.0.1.RELEASE or 1.5.1.RELEASE           | 1.7.0         | 1.1.4      | 4.4.0         | 2.7.3      | 0.9.0      |
| 2.1.0.RELEASE or 2.0.0.RELEASE or 1.5.0.RELEASE           | 1.6.3         | 1.1.1      | 4.4.0         | 2.7.3      | 0.7.1      |

关注公众号「站长严长生」，在手机上阅读所有教程，随时随地都能学习。本公众号由[C语言中文网站长](http://c.biancheng.net/view/8092.html)亲自运营，长期更新，坚持原创。