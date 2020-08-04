package com.axonivy.demo;

import java.util.Arrays;
import java.util.List;

import io.swagger.codegen.v3.ClientOptInput;
import io.swagger.codegen.v3.CodegenArgument;
import io.swagger.codegen.v3.CodegenConfig;
import io.swagger.codegen.v3.CodegenConstants;
import io.swagger.codegen.v3.DefaultGenerator;
import io.swagger.codegen.v3.config.CodegenConfigurator;

public class SwaggerCodeGenSimple {

	public static void main(String[] args)
	{
		CodegenConfigurator configurator = new CodegenConfigurator();
		configurator.setInputSpecURL("https://petstore.swagger.io:443/v2/swagger.json");
		//configurator.setLang(io.swagger.codegen.v3.generators.java.JavaClientCodegen.class.getName());
		configurator.setLang("java");
		configurator.setLibrary("jersey2");
		configurator.setModelPackage("io.swagger.petstore");
		
		List<CodegenArgument> genArgs = Arrays.asList(
				boolArg(CodegenConstants.MODEL_TESTS_OPTION, Boolean.FALSE),
				boolArg(CodegenConstants.MODEL_DOCS_OPTION, Boolean.FALSE),
				boolArg(CodegenConstants.API_TESTS_OPTION, Boolean.FALSE),
				boolArg(CodegenConstants.API_DOCS_OPTION, Boolean.FALSE)
		);
		configurator.getCodegenArguments().addAll(genArgs);
		configurator.setOutputDir("/mnt/data/dev/workspace/2020-06-mstr.rcp/designer-workspace/swagger-gen");
		
		try {
			System.setProperty(CodegenConstants.GENERATE_MODELS, "true");
			System.setProperty(CodegenConstants.SUPPORTING_FILES, "false");
			final ClientOptInput input = configurator.toClientOptInput();
			final CodegenConfig config = input.getConfig();
			
			config.additionalProperties().put("sourceFolder", "src_generated");
			config.additionalProperties().put("dateLibrary", "java8");
			
			new DefaultGenerator().opts(input).generate();
		} finally {
			System.clearProperty(CodegenConstants.SUPPORTING_FILES);
			System.clearProperty(CodegenConstants.GENERATE_MODELS);
		}
        
	}

	private static CodegenArgument boolArg(String option, Boolean value) {
	  return new CodegenArgument().option(option).type("boolean").value(value.toString());
	}
	
}
