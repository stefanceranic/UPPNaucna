naucnaCentrala.controller('odabirCasopisa', [
		'$scope',
		'$state',
		'$http',
		function($scope, $state, $http) {

			$scope.casopisi = [];
			$scope.properties = [];
			$scope.casopis={};

			$scope.pokreniProces = function() {
				$http.get("http://localhost:8080/process/start").then(
						function(response) {
							$scope.properties = response.data;
						}, function(response) {
						});
			};
			
			$scope.prikaziCasopise = function() {
				$http.get("http://localhost:8080/casopis/sviCasopisi").then(
						function(response) {
							$scope.casopisi = response.data;

						}, function(response) {
						});
			};

			$scope.odaberiCasopis = function(x) {
				$http.post("http://localhost:8080/casopis/odaberi/"+ $scope.properties.taskId,x)
                    .then(function(response) {
                        $state.go("prijavaRada",{"processId": $scope.properties.processInstanceId});
				}).then(function(value) {

				})
			}

			$scope.prikaziCasopise();
			$scope.pokreniProces();
		} ]);