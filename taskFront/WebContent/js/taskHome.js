var task=angular.module("task",["ngRoute"]);
task.config(function($routeProvider)
		{
			$routeProvider
			
			.when("/register",
					{
						templateUrl:"view/register.html",
						controller:"registerController"
				
					})
					
			.when("/login",
					{
						templateUrl:"view/login.html",
						controller:"loginController"
				
					})
			.when("/logout",
					{
						templateUrl:"view/logout.html",
						controller:"logoutController"
					})
			.when("/userhome",
					{
						templateUrl:'view/userhome.html',
						/*controller:'userHomeController'	*/			
					})
			.when("/task",
					{
						templateUrl:'view/task.html',
						controller:'taskController'					
					});
		});


task.controller("registerController",function($scope,$http)
	{
		console.log("in the register controller");
		$scope.register=function()
		{
			var reg={
					username:$scope.username,
					mail:$scope.mail,
					password:$scope.password
			};
			
			var result=$http.post("http://localhost:8888/task/saveUser",reg);
			result.success(function(data,status,headers,config)
					{
						console.log("status:"+status);
					})
				
		}
	});

task.controller("loginController",['$scope','$http','$location','$rootScope',function($scope,$http,$location,$rootScope)
     {
		console.log("in login controller");
		$scope.login=function()
		{
				
			var login={
					username:$scope.username,
					password:$scope.password
			          } 
			$http.post("http://localhost:8888/task/authenticate",login).then(function(response)
					{
				console.log("result data:"+response.data);
				var r=response.data.toString();
				console.log("response:"+r);
					
				if(r==1)
				{
				$rootScope.task=true;
				$rootScope.register=false;
				$rootScope.login=false;
				$rootScope.logout=true;
				$rootScope.name=$scope.username;
				console.log("name:"+$rootScope.name);
				$location.path('/userhome');
				
				}
				if(r==0)
				{
					$scope.username="";
					$scope.password="";
					$scope.message="username/password incorrect";
					$location.path('/login');
				}
				
				}); 
		}
     }]);



task.controller("taskController",function($scope,$http,$rootScope)
		{
	
	$rootScope.logout=true;
	$http.get("http://localhost:8888/task/viewMyTask/"+$rootScope.name).then(function (response) {$scope.tasks = response.data;});

			console.log("InTask Controller");
			console.log("Before the entry..")
			
		$scope.posttask=function()
		{
			var post={
					task:$scope.title,
					task_Description:$scope.task_Description,
					deadline:$scope.deadline,
					postedby:$rootScope.name
			};
			
			var result=$http.post("http://localhost:8888/task/saveTask",post);
			$http.get("http://localhost:8888/task/viewMyTask/"+$rootScope.name).then(function (response) {$scope.tasks = response.data;});
			result.success(function(data,status,headers,config)
					{
						console.log("status:"+status);
					})
			
						
		}
			
			$scope.editTask=function(task)
			{
				console.log("inside editblog");
				console.log("task:"+task);
				$scope.taskDataToEdit=task;
			}
			$scope.saveEdit=function()
			{
				var edit = {
		    				title:$scope.taskDataToEdit.title,
		    				task_Description:$scope.taskDataToEdit.task_Description,
		 					task_Id:$scope.taskDataToEdit.task_Id,
		 					deadline:$scope.taskDataToEdit.deadline
		 		};
				$http.put('http://localhost:8888/task/updateTask', edit);
				$http.get("http://localhost:8888/task/viewMyTask/"+$rootScope.name).then(function (response) {$scope.tasks = response.data;});
				result.success(function(data,status,headers,config)
						{
							console.log("status:"+status);
						})
				
			
			}			
			
			$scope.finished=function(task)
			{
				console.log("inside finishedtask");
				console.log("finished:"+task);
				$scope.taskstatus=task;
			}
				
				$scope.taskdone=function()
				{
					console.log("in finished");
					var stat=
					{
							task_Id:$scope.taskstatus.task_Id,
							title:$scope.taskstatus.title,
							task_Description:$scope.taskstatus.task_Description,
							deadline:$scope.taskstatus.deadline,
							postedby:$scope.name,
							status:true
					}
					
				$http.put("http://localhost:8888/task/updateTask",stat);
			$http.get("http://localhost:8888/task/viewMyTask/"+$rootScope.name).then(function (response) 
					
					{
					$scope.tasks = response.data;
					console.log("data:"+response.data);
					});
				}
			$http.get("http://localhost:8888/task/viewTask/"+$rootScope.name).then(function (response) 
					
					{
					$scope.done = response.data;
					console.log("data:"+response.data);
					});
			
			});
task.controller("logoutController",function($scope,$http,$rootScope)
		{
	$rootScope.task=false;
	$rootScope.register=true;
	$rootScope.login=true;
	$rootScope.logout=false;
		});

