naucnaCentrala.controller('urednikPregledFormataRada', [
		'$rootScope','$scope',
		'$state',
		'$http',
		'$stateParams',
		'FileSaver',
		'Blob',
		function($rootScope, $scope, $state, $http, $stateParams, FileSaver, Blob) {

			var rad = $stateParams.rad;
			var processInstanceId = $stateParams.processInstanceId;

			$scope.rad = {};
			
		    $scope.logout = function() {
		    	$rootScope.korisnik = "";
		    	$state.go("login"); 	
		    }

			$scope.vratiRad = function() {
				$http.get("http://localhost:8080/rad/vratiRad/" + rad).then(
						function(response) {
							$scope.rad = response.data;

						}, function(response) {
						});
			};

			$scope.preuzmiRad = function(y) {

				$http.post("http://localhost:8080/rad/download", y, {
					responseType : 'arraybuffer'
				}).then(function(response) {
					var myData = new Blob([ response.data ], {
						type : 'application/pdf;charset=utf-8'
					});
					FileSaver.saveAs(myData, 'nesto.pdf');
				}, function(response) {

				});

			};

			$scope.proveraRada = function(x) {
				$http.post("http://localhost:8080/urednik/proveraFormataRada/"+ processInstanceId, x)
					.then(function(response) {
					$state.go("pocetnaUrednik");
				}, function(response) {
				});

			}

			$scope.vratiRad();

		} ]);