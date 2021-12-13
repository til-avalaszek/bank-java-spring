package com.valaszek.bank.utils;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtils {

  public static Validator buildValidator() {
    PlatformResourceBundleLocator platform = new PlatformResourceBundleLocator("messages");
    MessageInterpolator interpolator = new ResourceBundleMessageInterpolator(platform);

    return Validation
        .byDefaultProvider()
        .configure()
        .messageInterpolator(interpolator)
        .buildValidatorFactory()
        .getValidator();
  }
}
