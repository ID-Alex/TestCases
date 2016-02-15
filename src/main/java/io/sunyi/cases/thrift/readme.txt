* 首先在 http://thrift.apache.org/download/  下载 thrift.

* 然后在一个 Linux 服务器上
	tar -zxvf thrift-0.9.0.tar.gz
	cd thrift-0.9.0
	./configure
	make 
	sudo make install

* \src\main\resource\org\alex\cases\thrift\SayService.thrift 放到服务器上.

	$ thrift -r --gen java SayService.thrift

* 把生成的 SayService.java 抓去下来.

* Maven pom 文件中添加:
		<!-- thrift -->
		<dependency>
			<groupId>org.apache.thrift</groupId>
			<artifactId>libthrift</artifactId>
			<version>0.9.0</version>
		</dependency>
		
* 剩下的看代码.   (*^__^*)