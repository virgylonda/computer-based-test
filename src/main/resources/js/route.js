/**
Author : Edric Laksa Putra
Since : June 2017
*/
cbtApp.config(function($stateProvider) {
	$stateProvider

	//===========================================================================//

	// AUTHENTIFICATION LOGIN ROUTE START HERE//

	//===========================================================================//

	.state('/', {
		views: {
					'main@': {
						controller: "IndexController"
					}
				},
		data: {
					displayName: 'Login'
			  }
	})
	.state('loginauthentification', {
		url: '/login',
		views: {
					'main@': {
						templateUrl: 'views/components/login.html',
						controller: "LoginController"
					}
				},
		data: {
					displayName: 'Login'
			  }
	})
	//===========================================================================//

	// ADMIN ROUTE START HERE//

	//===========================================================================//

	.state('home', {
		url: 'admin-dashboard/?userId',
		views: {
					'main@': {
						templateUrl: 'views/admin/admin-dashboard.html',
						controller: "AdminController"
					}
				},
		data: {
					displayName: 'Dashboard'
			  }
	})
	.state('home.testerlist', {
		url: '/testerlist',
		views: {
					'main@': {
						templateUrl: 'views/admin/datatester.html',
						controller : "AdminListTesterController"
					}
				},
		data: {
					displayName: 'Tester List'
			  }
	})
	.state('home.userlist', {
		url: '/userlist',
		views: {
					'main@': {
						templateUrl: 'views/admin/datausers.html',
						controller : "AdminListUsersController"
					}
				},
		data: {
					displayName: 'User List'
			  }
	})
	.state('home.editprofileadmin', {
		url: '/editprofileadmin',
		params : {
			dataUser : {

			}
		},
		views: {
					'main@': {
						templateUrl: 'views/admin/editprofileadmin.html',
						controller : "AdmiGetDetailController"
					}
				},
		data: {
					displayName: 'Profile Admin'
			  }
	})
	.state('home.editprofileadmin.confirmed', {
		url: '/confirmed',
		views: {
					'main@': {
						templateUrl: 'views/admin/admin-dashboard.html',
						controller : "AdminEditController"
					}
				},
		data: {
					displayName: 'Profile Admin'
			  }
	})
	.state('home.editprofileadmin.confirmedpass', {
		url: '/confirmedpass',
		views: {
					'main@': {
						templateUrl: 'views/admin/admin-dashboard.html',
						controller : "AdminEditPassController"
					}
				},
		data: {
					displayName: 'Profile Admin'
			  }
	})
	.state('home.testerlist.addtester', {
		url: '/addtester',
		views: {
					'main@': {
						templateUrl: 'views/admin/formtester.html',
						controller :"AdminAddTesterController"
					}
				},
		data: {
					displayName: 'Add New Tester'
			  }
	})
	.state('home.userlist.adduser', {
		url: '/adduser',
		views: {
					'main@': {
						templateUrl: 'views/admin/formusers.html',
						controller :"AdminAddUserController"
					}
				},
		data: {
					displayName: 'Add New User'
			  }
	})
	.state('home.testerlist.edittester', {
		url: '/edittester/?id',
		params : {
			dataUser : {

			}
		},
		views: {
					'main@': {
						templateUrl: 'views/admin/editprofiletester.html',
						controller : "AdminGetTesterController"
					}
				},
		data: {
					displayName: 'Tester Edit'
			  }
	})
	.state('home.testerlist.edittester.confirmtester', {
		url: '/confirmtester',
		views: {
					'main@': {
						templateUrl: 'views/admin/datatester.html',
						controller : "AdminEditTesterController"
					}
				},
		data: {
					displayName: 'Tester List'
			  }
	})
	.state('home.userlist.edituser', {
		url: '/edituser/?id',
		params : {
			dataUser : {

			}
		},
		views: {
					'main@': {
						templateUrl: 'views/admin/editprofileuser.html',
						controller : "AdminGetUserController"
					}
				},
		data: {
					displayName: 'User Edit'
			  }
	})
	.state('home.userlist.edituser.confirmuser', {
		url: '/confirmuser',
		views: {
					'main@': {
						templateUrl: 'views/admin/datausers.html',
						controller : "AdminEditUserController"
					}
				},
		data: {
					displayName: 'User List'
			  }
	})


	//===========================================================================//

	// TESTER ROUTE START HERE//

	//===========================================================================//

	.state('hometester', {
		url: '/tester-dashboard/?userId',
		views: {
					'main@': {
						templateUrl: 'views/tester/tester-dashboard.html',
					}
				},
		data: {
					displayName: 'Tester Dashboard'
			  }
	})
	.state('hometester.listcategories', {
		url: '/listcategories',
		views: {
					'main@': {
						templateUrl: 'views/tester/listcategories.html',
						controller: "TesterListCategories"
					}
				},
		data: {
					displayName: 'Category List'
			  }
	})
	.state('hometester.listcategories.addnewcategory', {
		url: '/addnewcategory',
		views: {
					'main@': {
						templateUrl: 'views/tester/formaddcategory.html',
						controller: "TesterAddCategories"
					}
				},
		data: {
					displayName: 'Category Add'
			  }
	})
	.state('hometester.listcategories.editcategory', {
		url: '/editcategory/?id',
		params : {
			dataCategory : {

			}
		},
		views: {
					'main@': {
						templateUrl: 'views/tester/editcategory.html',
						controller: "TesterGetCategoriesController"
					}
				},
		data: {
					displayName: 'Category Edit'
			  }
	})
	.state('hometester.listcategories.editcategory.confirmcategory', {
		url: '/confirmcategory',
		views: {
					'main@': {
						templateUrl: 'views/tester/listcategories.html',
						controller : "TesterEditCategoriesController"
					}
				},
		data: {
					displayName: 'Category Edit'
			  }
	})
	.state('hometester.listcategories.listquestion', {
		url: '/listquestion/?idCategory',
		views: {
					'main@': {
						templateUrl: 'views/tester/listquestion.html',
						controller: "TesterListQuestionController"
					}
				},
		data: {
					displayName: 'Question List'
			  }
	})
	.state('hometester.listcategories.addquestion', {
		url: '/addquestion/?idCategory',
		views: {
					'main@': {
						templateUrl: 'views/tester/formaddquestion.html',
						controller: "TesterAddQuestionController"
					}
				},
		data: {
					displayName: 'Question Add'
			  }
	})
	.state('hometester.listcategories.listquestion.updatequestion', {
		url: '/updatequestion/?id',
		views: {
					'main@': {
						templateUrl: 'views/tester/formeditquestion.html',
						controller: "TesterGetQuestionController"
					}
				},
		data: {
					displayName: 'Question Update'
			  }
	})
	.state('hometester.listcategories.listquestion.confirmquestion', {
		url: '/confirmquestion',
		params : {
			dataQuestion : {

			}
		},
		views: {
					'main@': {
						templateUrl: 'views/tester/listquestion.html',
						controller: "TesterEditQuestionController"
					}
				},
		data: {
					displayName: 'Question Confirm'
			  }
	})
	.state('hometester.userassign', {
		url: '/userassign',
		views: {
					'main@': {
						templateUrl: 'views/tester/formassigntest.html',
						controller: "TesterListUserController"
					}
				},
		data: {
					displayName: 'Tester Assign'
			  }
	})
	.state('hometester.userassign.listcategories', {
		url: '/listcategories/?userId',
		views: {
					'main@': {
						templateUrl: 'views/tester/listassignmentcategory.html',
						controller: "TesterAssignmentListController"
					}
				},
		data: {
					displayName: 'Assignment List Categories'
			  }
	})
	.state('hometester.userscore', {
		url: '/userscore',
		views: {
					'main@': {
						templateUrl: 'views/tester/formscores.html',
						controller: "TesterListScoresController"
					}
				},
		data: {
					displayName: 'Users Scores'
			  }
	})
	.state('hometester.userscore.detailscore', {
		url: '/detailscore/?userId',
		views: {
					'main@': {
						templateUrl: 'views/tester/formdetailscore.html',
						controller: "TesterScoresDetailController"
					}
				},
		data: {
					displayName: 'Users Scores'
			  }
	})

	//===========================================================================//

	// USER ROUTE START HERE//

	//===========================================================================//

	.state('homeuser', {
		url: '/user-dashboard/?userId',
		views: {
					'main@': {
						templateUrl: 'views/user/user-dashboard.html',
					}
				},
		data: {
					displayName: 'User Dashboard'
			  }
	})
	.state('homeuser.listtest', {
		url: '/listtest',
		views: {
					'main@': {
						templateUrl: 'views/user/listtest.html',
						controller : "UserShowTestController"
					}
				},
		data: {
					displayName: 'User List Test'
			  }
	})
});