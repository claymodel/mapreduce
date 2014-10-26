Hadoop Hive Compitibility for Hadoop-2.4.1 and Hive-0.13.1

ADD jar 'wasb://xxx@xxx.blob.core.windows.net/hive/azure-log-serde-1.0-SNAPSHOT.jar';

create table accesslogs(
bucketowner             string,
bucketname              string,
rdatetime               string,
rip                     string,
requester               string,
requestid               string,
operation               string,
rkey                    string,
requesturi              string,
httpstatus              int   ,
errorcode               string,
bytessent               int   ,
objsize                 int   ,
totaltime               int   ,
turnaroundtime          int   ,
referer                 string,
useragent               string,
versionid               string
)
ROW FORMAT SERDE 'jp.elias.azure.blob.serde.BlobLogDeserializer'
LOCATION 'wasb://xxx@xxx.blob.core.windows.net/HdiData/WebsiteLogData/log/'
TBLPROPERTIES ('skip.header.line.count'='1');
