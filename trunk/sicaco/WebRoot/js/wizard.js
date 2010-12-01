/* Copyright 2008 by Robert Baumgartner; the-godking.com
 * All rights reserved
 * You may use this script, as long as this copyright note won't be edited nor removed
 */
 
// Step by Step Wizard (code is ~ universal)

// User specific variables
	/*jQuery(document).ready(function() {
		var wizardStartByStepNmbr			= 1;									// Define by which step the wizard should start
		var hideAllStepApartCurrent			= true;									// Define if you want to hide steps, that are not currently selected
		var showCurrentStepNmbr				= true; 								// Define if the current step number should be shown 
		var showCurrentStepTitle			= true;									// Define wheter you want to show the step title or not
		var attrToGetStepTitle			= "title";								// Define in which attribute you store the step title
		var showCurrentStepText_Title	= "-"; 									// [Text] used to make this: Step 1 of 3 [Text] Steptitle
		var elementToTransformToWizard		= ".stepByStepWizard";					// Define the class into which you packed your single steps (use jQuery Syntax)
		var elementToAddNavi				= ".stepByStepWizardNavigation";		// Define to which element you want to add the navigation
		var showCurrentStepText_step	= "Schritt "; 							// Text used for "Step"
		var showCurrentStepText_of		= "/"; 								// Text used for "/"
		var showCurrentStepText_forward	= "&nbsp;&nbsp;&raquo;&nbsp;&nbsp;"; 	// Text used for "Forward"
		var showCurrentStepText_back	= "&nbsp;&nbsp;&laquo;&nbsp;&nbsp;"; 	// Text used for "Back"
*/
	// Script specific variables
function wizardConfig(wizardStartByStepNmbr,hideAllStepApartCurrent,showCurrentStepNmbr,showCurrentStepTitle,attrToGetStepTitle,showCurrentStepText_Title,elementToTransformToWizard,elementToAddNavi,showCurrentStepText_step,showCurrentStepText_of,showCurrentStepText_forward,showCurrentStepText_back){
	var currentStepPosition				= wizardStartByStepNmbr;						// Which step is current shown
	var differentStepsNmbr 				= jQuery(elementToTransformToWizard).size();	// How many steps does the wizard consists in?
	
	
	// CODE --- DO NOT CHANGE //
					
	// Hide all steps
	jQuery(elementToTransformToWizard).hide();
	
	// Function the endue each step with an unique class
	function setStepUniqueClass() {
		var differentStepsNmbrLoop = differentStepsNmbr;
		var uniqueClass = 1; // Counter
		jQuery(elementToTransformToWizard).addClass("temporaryStepClass");
		while(differentStepsNmbrLoop > 0) {
			differentStepsNmbrLoop--;
			jQuery(".temporaryStepClass:first").addClass("step_nmbr_"+uniqueClass);
			jQuery(".step_nmbr_"+uniqueClass).removeClass("temporaryStepClass");
				//alert("step_nmbr_"+uniqueClass);
				uniqueClass++;
		};
	};
	
	// Function to show the current step position
	var firstTimeToExecuteFunction_showCurrentStepPosition = 0;
	
	function showCurrentStepPosition() {
		if(showCurrentStepNmbr == true) {
			if (firstTimeToExecuteFunction_showCurrentStepPosition == 0) {
				jQuery(elementToTransformToWizard+":first").before("<div class=\"floatLeft showCurrentStepNmbrDIV\"></div><div class=\"floatRight stepByStepWizardNavigation\" id=\"stepByStepWizardNavigation\"></div><div class=\"clearBoth\"></div>");
				firstTimeToExecuteFunction_showCurrentStepPosition++;
				//alert("Was the first time");
			};
			if(showCurrentStepTitle == true) {
				// Get step title
				currentStepTitle  = " "+showCurrentStepText_Title+" ";
				currentStepTitle += jQuery(".step_nmbr_"+currentStepPosition).attr(""+attrToGetStepTitle+"");
			} else {
				currentStepTitle = "";
			}
			jQuery(".showCurrentStepNmbrDIV").html(""+showCurrentStepText_step+" "+currentStepPosition+" "+showCurrentStepText_of+" "+differentStepsNmbr+""+currentStepTitle+"");
		};
	};
	
	// Function to add a forward button
	var firstTimeToExecuteFunction_addForwardLink = 0;
	
	function addForwardLink() {
		if (firstTimeToExecuteFunction_addForwardLink == 0) {
			jQuery(elementToAddNavi+"").append("<div class=\"showNavigationLinksForwardDIV\"></div>");
			firstTimeToExecuteFunction_addForwardLink++;
		};
		
		if(currentStepPosition < differentStepsNmbr) {
			currentStepPositionForward = currentStepPosition;
			currentStepPositionForward++;
			jQuery(".showNavigationLinksForwardDIV").html("<span name=\""+currentStepPositionForward+"\" class=\"stepByStepWizard_forwad stepByStepWizard_goto\">"+showCurrentStepText_forward+"<!--("+showCurrentStepText_step+" "+currentStepPositionForward+")--></span> ");
		} else {
			jQuery(".showNavigationLinksForwardDIV").html("");
		};
		jQuery(".stepByStepWizard_goto").click(function() {
				gotoStepNmbr = jQuery(this).attr("name");
				changeStep(gotoStepNmbr);
		});
	};
	
	// Function to add a back button
	var firstTimeToExecuteFunction_addBackLink = 0;
	
	function addBackLink() {
		if (firstTimeToExecuteFunction_addBackLink == 0) {
			jQuery(elementToAddNavi+"").append("<div class=\"showNavigationLinksBackDIV\"></div>");
			firstTimeToExecuteFunction_addBackLink++;
		};
							
		if(currentStepPosition <= differentStepsNmbr) {
			if(currentStepPosition > 1) {
				currentStepPositionBack = currentStepPosition;
				currentStepPositionBack--;
				jQuery(".showNavigationLinksBackDIV").html("<span name=\""+currentStepPositionBack+"\" class=\"stepByStepWizard_back stepByStepWizard_goto\">"+showCurrentStepText_back+"<!--("+showCurrentStepText_step+" "+currentStepPositionBack+")--></span>");
			} else {
				jQuery(".showNavigationLinksBackDIV").html("");
			};
		} else {
			jQuery(".showNavigationLinksBackDIV").html("");
		};
		jQuery(".stepByStepWizard_goto").click(function() {
				gotoStepNmbr = jQuery(this).attr("name");
				changeStep(gotoStepNmbr);
		});
	};
	
	// Function to change to another step
	function changeStep(changeToStepNmbr) {
		// Hide stes that are not used anymore (if user wants to...)
		if(hideAllStepApartCurrent == true) {
			jQuery(elementToTransformToWizard).hide();
		};
		
		currentStepPosition = changeToStepNmbr;
		// Show new step
		jQuery(".step_nmbr_"+changeToStepNmbr).show();
		
		// Renew navigation
		showCurrentStepPosition();
		if(hideAllStepApartCurrent == true) {
			addBackLink();
		};
		addForwardLink();
	};
	
	
	setStepUniqueClass();
	showCurrentStepPosition();
	
	// Create Navigation
		addForwardLink();
		addBackLink();
		

		// Change to another step (do forward / back)
		jQuery(".stepByStepWizard_goto").click(function() {
				gotoStepNmbr = jQuery(this).attr("name");
				changeStep(gotoStepNmbr);
		});
// Show the start-Step
jQuery(".step_nmbr_"+wizardStartByStepNmbr).show();
// End Step by Step Wizard
}