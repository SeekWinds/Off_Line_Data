#!/bin/bash

process_data=20180717

echo "step1:mapreduce etl"
#安装常理输出到分区里；输出参数加上day=20180717
hadoop jar /opt/scripts/g6-hadoop-1.0.jar com.ruozedata.hadoop.mapreduce.driver.LogETLDriver /input /home/hadoop/data/output/day=$process_data

#数据刷到hive中
echo "step2:hdfsdata mv hive"
hadoop fs -rm -r /home/hadoop/data/clear/day=$process_data
hadoop fs -mkdir -p /home/hadoop/data/clear/day=$process_data
hadoop fs -mv /home/hadoop/data/output/day=$process_data/part-r-00000 /home/hadoop/data/clear/day=$process_data

echo "step3:Brush the metadata"
hive -e "alter table test.hadoop_access add if not exists partition(day=$process_data)"
